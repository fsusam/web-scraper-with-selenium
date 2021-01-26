package com.fet.tutorial.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) {
        WebDriver driver = null;
        int pageLimit = 2;
        try{
            System.setProperty("webdriver.chrome.driver", "/home/fsusam/Development/workspaces/java/src/main/resources/chromedriver");
            driver = new ChromeDriver();
            driver.get("https://injuriesandsuspensions.com/");
            WebElement elementCenterBlogIndex =  driver.findElement(By.xpath("/html/body/div[1]/div[4]"));
            List<WebElement> elementPostBoxIndexes = elementCenterBlogIndex.findElements(By.className("post-box-index"));
            System.out.println("Number of elements:" +elementPostBoxIndexes.size());

            WebDriverWait wait = new WebDriverWait(driver, 10);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            WebElement elementPageNumber = driver.findElement(By.id("wp_page_numbers"));
            List<WebElement> pageNumbers = elementPageNumber.findElements(By.tagName("li"));
            for(WebElement pageElement:pageNumbers){
                if(pageElement.getText().contains("Page"))
                    continue;
                if(pageElement.getText().equals(""+(pageLimit+1)))
                    break;
                System.out.println(pageElement.getText());
            }

            for(WebElement elementPostBoxIndex:elementPostBoxIndexes){
                System.out.println(elementPostBoxIndex.findElement(By.tagName("a")).getText());
                System.out.println(elementPostBoxIndex.findElement(By.className("post-date")).getText());
                System.out.println(elementPostBoxIndex.findElement(By.className("post-content-category")).getText());
                try{
                    By quickPick = By.className("quick_pick");
                    By quickPickHidden = By.className("quick_pick_hiden");

                    WebElement quickPickElement = elementPostBoxIndex.findElement(quickPick);


                    wait.until(ExpectedConditions.visibilityOf(quickPickElement));
                    //System.out.println(quickPickElement.getText());


                    jse.executeScript("arguments[0].click()", quickPickElement);



                    WebElement quickPickHiddenElement = elementPostBoxIndex.findElement(quickPickHidden);

                    wait.until(ExpectedConditions.visibilityOf(quickPickHiddenElement));

                    //System.out.println(quickPickHiddenElement.getAttribute("style"));
                    System.out.println("####content####");
                    WebElement contentTeamBox = quickPickHiddenElement.findElement(By.className("content_team_box"));
                    System.out.println(contentTeamBox.findElement(By.className("content_team_box_title")).getText());
                    WebElement contentTeamBoxTeamsLeft = quickPickHiddenElement.findElement(By.className("content_team_box_teams_left"));
                    //System.out.println(contentTeamBoxTeamsLeft.findElement(By.className("content_team_box_teams_title")).getText());
                    List<WebElement> contentTeamBoxTeamsLeftItems = contentTeamBoxTeamsLeft.findElements(By.tagName("p"));
                    if(contentTeamBoxTeamsLeftItems!=null){
                        for(WebElement item : contentTeamBoxTeamsLeftItems){
                            System.out.println(item.getText());
//                            List<WebElement> spanList =  item.findElements(By.tagName("span"));
//                            for(WebElement span:spanList){
//                                System.out.println(span.getText());
//                            }
                        }
                    }

                    WebElement contentTeamBoxTeamsRight = quickPickHiddenElement.findElement(By.className("content_team_box_teams_right"));
                    List<WebElement> contentTeamBoxTeamsRightItems = contentTeamBoxTeamsRight.findElements(By.tagName("p"));
                    if(contentTeamBoxTeamsRightItems!=null){
                        for(WebElement item : contentTeamBoxTeamsRightItems){
                            System.out.println(item.getText());
//                            List<WebElement> spanList =  item.findElements(By.tagName("span"));
//                            for(WebElement span:spanList){
//                                System.out.println(span.getText());
//                            }
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Not clickable");

                }
                System.out.println("-------------------------------");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.close();
            driver.quit();
        }


    }
}
