package com.biblioConvert.maven.eclipse.Extension;

/**
 * Клас з додатковими методами, які не реалізовані в стандартних бібліотеках
 */
public class ExtensionString {
    /**
     * Метод для замінити елемента рядкового типу на новий з кінця рядка
     * @param extension_string рядок з яким будуть відбуватися зміни
     * @param oldValue елемент який потрібно замінити
     * @param newValue новий елемент який потрібно поставити на місце старого
     * @return Повертає рядок текстового типу
     */
    public static String replaceEnd(String extension_string, String oldValue, String newValue) {
        int index = extension_string.lastIndexOf(oldValue);
        return extension_string.substring(0, index) + newValue + extension_string.substring(index + oldValue.length());
    }
}

