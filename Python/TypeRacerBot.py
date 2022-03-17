import cv2
import pytesseract
import time
from selenium import webdriver


class TyperaceBot:
    def __init__(self):
        # Opening the Typeracer website
        self.driver = webdriver.Chrome()
        self.driver.get("https://play.typeracer.com/")
        time.sleep(5)

        # Clicking on the "Enter a typing race"
        enter_race = self.driver.find_element_by_xpath("/html/body/div[1]/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div[1]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/a")
        enter_race.click()
        time.sleep(7)

        # Get the text from the website
        type_text = self.driver.find_element_by_xpath("/html/body/div[1]/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div[1]/div/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td/div/div").text
        print(type_text)

        # Wait till the countdown is zero and start typing the text in the answer box
        while True:
            countdown_box = self.driver.find_element_by_xpath(
                "/html/body/div[6]/div/table/tbody/tr/td/table/tbody/tr/td[3]/div/span").text
            if countdown_box == ":00":
                time.sleep(0.5)
                break

        # Start typing in the answer box
        answer_box = self.driver.find_element_by_xpath("/html/body/div[1]/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div[1]/div/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/input")
        answer_box.click()
        answer_box.send_keys(type_text)
        for letter in type_text:
            answer_box.send_keys(letter)
            delay = 9 / 1000
            time.sleep(delay)


my_typing_bot = TyperaceBot()
