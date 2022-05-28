package com.biblioConvert.maven.eclipse.Class;

import com.biblioConvert.maven.eclipse.Abstract.ConvertVariantFile;
import com.biblioConvert.maven.eclipse.Enum.*;
import com.biblioConvert.maven.eclipse.Form.ErrorFragment;
import com.biblioConvert.maven.eclipse.Form.NewIgnoreMark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Клас для розбиття файлу на фрагменти, якщо вибраний або SCOPUS або WOS варіант конвертування
 */
public class RIS_Break_Fragments extends ConvertVariantFile
{
    private String convertType;
    private ArrayList<String> fragment = new ArrayList<>();
    private RIS_TO_TXT_Convert convert;

    /**
     * Метод, який отримує вид конвертатора, для того щоб знати за якими правилами виконувати конвертування даних
     * @param convertType вид конвертатора
     */
    public RIS_Break_Fragments(String convertType) {
    	this.convertType = convertType;
    }

    @Override
    public void StartConvert() {
        String sentence, identifier;
        convert = new RIS_TO_TXT_Convert(convertType);

        try {
            File file = new File(getNameOpenFile());
            BufferedReader fileRead  = new BufferedReader(new FileReader(file));
            while ((sentence = fileRead.readLine()) != null){
                if(!sentence.equals("")) {
                    fragment.add(sentence);
                }
                if(sentence.startsWith("ER")) {
                    identifier = Check_Identifier(fragment.get(0));
                    if (identifier.equals("(не обрано)"))
                    {
                        fragment.clear();
                        continue;
                    }
                    convert.setFragment(fragment);
                    convert.StartConvert(getNameSaveFile(), identifier);
                    fragment.clear();
                }
            }
            fileRead.close();
            Unknown_Tags();
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Виникла помилка \n\"" + ex.getMessage() + "\"\n Програму завершено.", "Увага", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Метод в якому відбувається отримання індентифікатора даного фрагмента для подальшої обробки даних. Якщо фрагмент не вдалося індентифікувати, то виведеться ручний вибір індентифікатора.
     * @param stringFragment отримує рядок в якому визнається індентифікатор даного фрагменту
     * @return Повертає змінну типу String
     */
    private String Check_Identifier(String stringFragment)
    {
        String identifier, size_delete_dash = "  - ";
        try
        {
            int start_position = stringFragment.indexOf(size_delete_dash) + size_delete_dash.length();
            identifier = stringFragment.substring(start_position);
        }
        catch (Exception ex)
        {
        	ErrorFragment error = new ErrorFragment(fragment, convertType);
        	JDialog frame = new JDialog(error.getJFrame(), "Невідомий фрагмент", true);
        	frame.getContentPane().add(error.getJFrame().getContentPane());
        	frame.pack();
        	frame.setBounds(100, 100, 725, 640);
        	frame.setResizable(false);
        	frame.setVisible(true);
        	identifier = error.getQueryType();
        }
        return identifier;
    }

    /**
     * Метод для виявлення міток про які нічого не відомо
     */
    private void Unknown_Tags() {
        DialogResult reply = DialogResult.None;
        try {
            if(convert.getColection().size() != 0) {
                FileWriter writer = new FileWriter(Function.getSystemFile(System_File_Code.Unknown_Tags));
                final Date date = new Date();
                @SuppressWarnings("serial")
				ArrayList<String> form = new ArrayList<String>(){{
                    add("*****");
                    add("Дата і час: " + date.toString());
                    add("Файл з вихідними даними: " + getNameOpenFile());
                    add("Конвертований файл: " + getNameSaveFile());
                    add("Тип конвертатора: " + convertType);
                }};
                String text = "";
            	for(String element : convert.getColection()) {
            		text += element + ", ";
            	}
                form.add("Нерозпізнані мітки: " + text);
                writer.write(Function.getJoin("\n", form));
                form.add("*****");
                
                NewIgnoreMark ignoreMark = new NewIgnoreMark(form, convert.getColection());
            	JDialog frame = new JDialog(ignoreMark.getJFrame(), "Невідомі мітки", true);
            	frame.getContentPane().add(ignoreMark.getJFrame().getContentPane());
            	frame.pack();
            	frame.setBounds(100, 100, 660, 620);
            	frame.setResizable(false);
            	frame.setVisible(true);
                reply = ignoreMark.getReply();
                convert.getColection().clear();
            }
            switch (reply)
            {
                case Yes:
                    {
                        BufferedWriter rebootFile = new BufferedWriter(new FileWriter(getNameSaveFile()));
                        rebootFile.write("");
                        rebootFile.close();
                        StartConvert();
                        JOptionPane.showMessageDialog (null, "Файл було перекомпільовано з урахуванням вашого вибору.","Увага", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                case No:
                    {
                    	JOptionPane.showMessageDialog (null, "Незадокументовані мітки не було конвертовано.","Увага", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                default: break;
            }
        }
        catch (FileNotFoundException el) {
            JOptionPane.showMessageDialog (null, "Виникла помилка під час відкриття файлу.\n" + el.getMessage() + "\nПрограму завершено!", "Увага", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog (null, "Під час введення/виведення даних в/з файлу відбувся збій.\n" + ex.getMessage() + "\nПрограму завершено!", "Увага", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
}

