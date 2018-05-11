package ru.job4j;

/**
 *Класс Calculate предназначен для вывода строки
 *"Hello, world!" в консоль.
 *@author Kuchumov
 *@since 09.05.2018
 */

public class Calculate {
	
	/**
	 *Метод main предназначен для вывода строки
	 *"Hello, world!" в консоль.
	 *@param args - args
	 */
	
	public static void main(String[] args) {
		System.out.println("Hello, world!");
	}
	
	/**
	 * Method echo.
	 * @param name Your name.
	 * @return Echo plus your name.
	 */
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}
}