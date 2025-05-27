package tests;

import org.testng.annotations.Test;

import base.BaseTest;

public class RegisterTest extends BaseTest{
    
    @Test
    public void testePiloto() {
        String title = driver.getTitle();
        System.out.println("Title of the page is: " + title);
    }
}
