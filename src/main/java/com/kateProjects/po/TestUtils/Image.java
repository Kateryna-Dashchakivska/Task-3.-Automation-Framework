package com.kateProjects.po.TestUtils;

import com.kateProjects.po.Driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Finder;
import org.sikuli.script.Pattern;
import sun.applet.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    
    public static WebDriver driver = DriverFactory.getDriver("chrome");

    public static Pattern takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
//Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
//Call getScreenshotAs method to create image file
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
//Move image file to new destination
        File destFile = new File(fileWithPath);
//Copy file at destination
        FileUtils.copyFileToDirectory(srcFile, destFile);
        return new Pattern();
    }

    public static final Pattern PIC1 = new Pattern(Main.class.getResource("Flow_start"));

    public static boolean compareTwoPics(Pattern p1, Pattern p2) throws Exception {
        Finder f1 = new Finder(p1);
        f1.find(p2);

        if (f1.hasNext())
        {
            return true;
        } else {
            return false;}

    }


        public static void takeScreen (String Name) throws IOException, AWTException {
        /*BufferedImage bImage = null;
        File initialImage = new File("C:\\Users\\Kateryna_Dashchakivs\\IdeaProjects\\Task-3.-Automation-Framework\\src\\main\\resources\\pic1.png");
        bImage = ImageIO.read(initialImage);
        ImageIO.write(bImage, "gif", new File("C:\\Users\\Kateryna_Dashchakivs\\IdeaProjects\\Task-3.-Automation-Framework\\src\\main\\resources\\pic2.gif"));*/

            Robot robot = new Robot();
            BufferedImage myScreenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(myScreenshot, "png", new File(Name));

        /*new File("Test_Picture").mkdir();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);

        BufferedImage image = robot.createScreenCapture(screenRect);
        utilsLogger.info(ImageIO.write(image, "png", new File("//"
                + Name)));*/

//        Screen screen = new Screen();
//        Match result = screen.exists(PIC1);
//        System.out.println("Pass = " + result);
        }

    }


