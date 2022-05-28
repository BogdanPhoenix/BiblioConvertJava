package com.biblioConvert.maven.eclipse.Class;

import com.biblioConvert.maven.eclipse.Enum.System_File_Code;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * Клас для перевірки системних файлів перед запуском програми
 */
public class Function {
    private static final String nameFolder = "File_Info";
    @SuppressWarnings("serial")
	private static final HashMap<System_File_Code, String> systemFile = new HashMap<System_File_Code, String>(){{
        put(System_File_Code.Country_Code, "Country_Code.json" );
        put(System_File_Code.Language_Code,  "Language_Code.json" );
        put(System_File_Code.Mark_from_RIS_to_TXT, "Mark_from_RIS_to_TXT.json");
        put(System_File_Code.Mark_Ignore, "Mark_Ignore.json");
        put(System_File_Code.Unknown_Tags, "Unknown_Tags.txt");
    }};

    /**
     * Метод для отримання одного із системних файлів
     * @param index - параметр за яким здійснюється пошук необхідного файлу
     * @return Повертає стрінгове значення, назву файлу.
     */
    public static String getSystemFile(System_File_Code index) { return Paths.get(nameFolder, systemFile.get(index)).toString(); }

    /**
     * Метод для перевірки чи всі системні файли на місті і чи не є вони пустими
     * @return Повертає булеве значення.
     * true - все добре, програма може працювати;
     * false - виникли проблеми зі системними файлами.
     */
    public static Boolean systemCheck() {
        try {
            String exePath = Paths.get("").toAbsolutePath().toString();
            for(Map.Entry<System_File_Code, String>  element : systemFile.entrySet()){
                if(!new File(Paths.get(exePath, nameFolder, element.getValue()).toString()).exists()){
                    JOptionPane.showMessageDialog(null, "Відсутній файл \"" + element.getValue() + "\" в каталозі програми. Програму завершено.", "Увага", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Виникла помилка \n\"" + ex.getMessage() + "\" в каталозі програми. Програму завершено.", "Увага", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static String getJoin(String symbol, ArrayList<String> data) {
    	String text = "";
    	
    	for(String element : data) {
    		text += element + symbol;
    	}
    	
    	return text;
    }
}

