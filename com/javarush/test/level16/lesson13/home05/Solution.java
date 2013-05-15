package com.javarush.test.level16.lesson13.home05;

/**
 * JavaRush.ru
 * Level 16, Lesson 13, Home 05
 * <p/>
 * Взаимная блокировка.
 * 1. Разберись, как работает программа.
 * 2. Не меняя классы T1 и T2 сделай так, чтобы они отработали и завершились.
 * <p/>
 * Date: 10.05.13
 * @author Sergey Kandalintsev
 */
public class Solution
{
    static Thread t1 = new T1();
    static Thread t2 = new T2();

    public static void main( String[] args ) throws InterruptedException
    {
        t1.start();
        t2.start();
        t1.interrupt();
    }

    public static class T1 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                t2.join();
                System.out.println( "T1 finished" );
            }
            catch ( InterruptedException e )
            {
                System.out.println( "T1 was interrupted" );
            }
        }
    }

    public static class T2 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                t1.join();
                System.out.println( "T2 finished" );
            }
            catch ( InterruptedException e )
            {
                System.out.println( "T2 was interrupted" );
            }
        }
    }
}