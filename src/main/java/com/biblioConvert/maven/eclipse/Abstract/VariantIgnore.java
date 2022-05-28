package com.biblioConvert.maven.eclipse.Abstract;

import java.util.ArrayList;

/**
 * Абстрактний клас, який реалізує змінні та методи, які необхідні при визначенні міток які потрібно ігнорувати в залежності від типу конвертування
 */
public abstract class VariantIgnore {
    private ArrayList<String> all;
    private ArrayList<String> jour;
    private ArrayList<String> book;

	/*public void setAll(ArrayList<String> all) { this.all = all; }
	public void setJour(ArrayList<String> jour) { this.jour = jour; }
	public void setBook(ArrayList<String> book) { this.book = book; }*/

    /**
     * Метод для виведення міток які ігноруються лише у вибраних варіантах або SCOPUS або WOS, але в не залежать від типу
     * @return Повертає динамічну структуру даних ArrayList
     */
    public ArrayList<String> getAll() { return all; }

    /**
     * Метод для виведення міток які ігноруються лише у вибраних варіантах або SCOPUS або WOS, тип - JOUR
     * @return Повертає динамічну структуру даних ArrayList
     */
    public ArrayList<String> getJour() { return jour; }

    /**
     * Метод для виведення міток які ігноруються лише у вибраних варіантах або SCOPUS або WOS, тип - BOOK
     * @return Повертає динамічну структуру даних ArrayList
     */
    public ArrayList<String> getBook() { return book; }
}

