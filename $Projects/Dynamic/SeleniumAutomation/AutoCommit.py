#---------------------------------------------------------------------------------------
# Script to login and creat commits for github commit history performance             --
#---------------------------------------------------------------------------------------

# set to how many times you would like to run -----------
limit = 7  
# -------------------------------------------------------

from selenium.webdriver import Firefox
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.service import Service
from selenium.webdriver.common.by import By

# set up browser
firefoxOptions = Options()
firefoxOptions.binary_location = r"C:\\Program Files\\Mozilla Firefox\\firefox.exe"
service = Service()
driver = Firefox(service=service, options=firefoxOptions)

# <summary>
# Login to github
# </summary>
def loginToGitHub():

    # open github on firefox
    driver.get("https://github.com/login")

    # initailze the user and pass
    email = ""
    password = ""

    driver.maximize_window()
    enterEmail = driver.find_element("id", "login_field")
    enterEmail.send_keys(email)
    enterPass = driver.find_element("id", "password")
    enterPass.send_keys(password)
    submitLogin = driver.find_element(By.XPATH, "/html/body/div[1]/div[3]/main/div/div[4]/form/div/input[13]")
    submitLogin.click()

    driver.implicitly_wait(1000)



# <summary>
# Open the bootcamp repo to edit
# </summary>
def openRepository():
    openRepo = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/div/aside/div/div/loading-context/div/div[1]/div/ul/li[1]/div/div/a")
    openRepo.click()
    # userBtn = driver.find_element(By.XPATH, "//*[@id='dialog-show-dialog-d79c6c8b-1576-4191-b661-cf1e0883dec9']")
    # userBtn.click()
    # repoBtn = driver.find_element(By.Id, "//*[@id='item-454af4ac-fd81-40b0-85ae-f0c0cf02726f']")
    # repoBtn.click()
    # bootcamp = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/main/div/div/div[2]/turbo-frame/div/div[2]/ul/li[2]/div[1]/div[1]/h3/a")
    # bootcamp.click()

    driver.implicitly_wait(100)


# <summary>
# Open the file to be edited
# </summary>
def openFile():
    # click on file to edit
    file = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div/div[2]/div[1]/div[4]/div[3]/div[1]/div[6]/div[2]/span/a")
    file.click()

    driver.implicitly_wait(100)

# <summary>
# Open the edit page to create a difference to commit
# </summary>
def editRepository():

    # start editing
    editBtn = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/main/turbo-frame/react-app/div/div/div[2]/div[1]/div/div/main/div[2]/div/div[3]/div[3]/div/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/span/a")
    editBtn.click()
    # write to the textbox
    textBox = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/main/turbo-frame/react-app/div/div/div[2]/div[1]/div/div/main/div[2]/div/div[3]/div[1]/div[1]/div/div[2]/span[2]/input")
    textBox.send_keys("$")

# <summary>
# Commit any changes
# </summary>
def commit():
    commit = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/main/turbo-frame/react-app/div/div/div[2]/div[1]/div/div/main/div[2]/div/div[3]/div[1]/div[2]/button")
    commit.click()
    driver.implicitly_wait(100)
    commitConfirm = driver.find_element(By.XPATH, "/html/body/div[1]/div[6]/div/main/turbo-frame/react-app/div/div/div[1]/div/div/div/div[3]/button[2]")
    commitConfirm.click()


# <summary>
# Kill the active browser window
# </summary>
def killDriver():
    driver.quit()


# ----------- Function Calls -------------

count = 0   # always set to 0

loginToGitHub()
openRepository()
openFile()

while (count <= limit):

    editRepository()
    commit()

    count = count + 1

killDriver()
