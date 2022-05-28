package com.biblioConvert.maven.eclipse.Abstract;

/**
 * Абстрактний клас, який реалізує змінні та методи, які необхідні при базовій конвертації даних
 */
public abstract class ConvertVariantFile {
    private String nameOpenFile;
    private String nameSaveFile;

    /**
     * Метод, який повертає шлях до файлу, який потрібно конвертувати
     * @return Повертає змінну типу String
     */
    public String getNameOpenFile() { return nameOpenFile; }
    /**
     * Метод, який отримує шлях до файлу, який потрібно конвертувати
     * @param nameOpenFile назва файлу, який потрібно відкрити
     */
    public void setNameOpenFile(String nameOpenFile) { this.nameOpenFile = nameOpenFile; }

    /**
     * Метод, який повертає шлях до файлу, в який будуть зберігатися конвертовані дані
     * @return Повертає змінну типу String
     */
    public String getNameSaveFile() { return nameSaveFile; }

    /**
     * Метод, який отримує шлях до файлу, в який будуть зберігатися конвертовані дані
     * @param nameSaveFile назва файлу, який потрібно відкрити
     */
    public void setNameSaveFile(String nameSaveFile) { this.nameSaveFile = nameSaveFile; }

    /**
     * Абстрактний метод для початку конвертації
     */
    public abstract void StartConvert();
}

