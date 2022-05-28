package com.biblioConvert.maven.eclipse.Class;

import com.biblioConvert.maven.eclipse.Abstract.VariantIgnore;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Клас для RIS to TXT варіанту конвертування за вказаним ідентифікатором
 */
public class RIS_TO_TXT_Convert {
    private final ALL_RIS_TXT menu = new ALL_RIS_TXT();
    private ArrayList<String> fragment = new ArrayList<>();
    private ArrayList<String> ignoreALL = new ArrayList<>();
    private VariantIgnore variantIgnore = null;
    private HashSet<String> colection = new HashSet<String>();
    private Boolean check;

    /**
     * Отримання фрагменту для конвертування
     * @param fragment фрагмент який потрібно конвертувати
     */
    public void setFragment(ArrayList<String> fragment) { this.fragment = fragment; }
    /**
     * Повертає колекцію про незадокументовані мітки
     * @return повертає колекції
     */
    public HashSet<String> getColection() { return colection; }

    /**
     * Конструктор
     * @param convertType - вид конвертування
     */
    public RIS_TO_TXT_Convert(String convertType) {
        switch (convertType) {
            case "SCOPUS (RIS to TXT)" : {
                ignoreALL = menu.getIgnore().getScopus().getAll();
                variantIgnore = menu.getIgnore().getScopus();
                break;
            }
            case "WoS (RIS to TXT)" : {
                ignoreALL = menu.getIgnore().getWos().getAll();
                variantIgnore = menu.getIgnore().getWos();
                break;
            }
        }
    }

    /**
     * Старт конвертування
     * @param fileNameSave шлях до файлу в який потрібно зберегти конвертовані дані
     * @param identifier ідентифікатор конвертування
     */
    public void StartConvert(String fileNameSave, String identifier) {
        menu.setFragment(fragment);
        menu.setIndex(0);
        int startIndex = 0, markLength = 2;

        for(; menu.getIndex() < fragment.size(); menu.setIndex(menu.getIndex() + 1)) {
            menu.setPart(fragment.get(menu.getIndex()).substring(startIndex, markLength));
            switch(identifier) {
                case "JOUR":
                    if (!menu.Check(variantIgnore.getJour(), ignoreALL))
                        check = menu.Jour();
                    break;
                case "CPAPER":
                    if (!menu.Check(menu.getIgnore().getWos().getCpaper(), ignoreALL))
                        check = menu.Cpaper();
                    break;
                case "BOOK":
                    if (!menu.Check(variantIgnore.getBook(), ignoreALL))
                        check = menu.Book();
                    break;
                case "CONF":
                    if (!menu.Check(menu.getIgnore().getScopus().getConf(), ignoreALL))
                        check = menu.Conf();
                    break;
            }
            if(!check) {
                Free_Transformation();
            }
            Find_Mark();
        }
        menu.WriterFile(fileNameSave, fragment);
    }

    /**
     * Конвертування окремих міток за певними правилами
     */
    private void Free_Transformation()
    {
        switch (menu.getPart()) {
            case "DB" : menu.Work_DB(); break;
            case "AU" : menu.Work_AU(); break;
            case "A2" : menu.Work_A2(); break;
            case "N1" : menu.Work_N1(); break;
            case "AD" : menu.Work_AD(); break;
            case "LA" : menu.Work_LA(); break;
            case "UR" : menu.Work_UR(); break;
            case "AN" : menu.Work_AN_WOS(); break;
            default : menu.Work_Default(); break;
        }
    }
    /**
     * Пошук міток про які нічого не відомо
     */
    public void Find_Mark()
	{
    	if(fragment.get(menu.getIndex()).startsWith(menu.getPart() + ALL_RIS_TXT.getSizeDeleteDash())) {
    		colection.add(menu.getPart());
    	}
	}
}

