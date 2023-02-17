from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup as bs
import time
import os
from urllib import request

# 크롬 드라이버 자동 업데이트
from webdriver_manager.chrome import ChromeDriverManager

# 브라우저 꺼짐방지
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)

service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=chrome_options)

text_keyword = "캐주얼아우터"

url = "https://store-kr.uniqlo.com/search/searchUniqlo.lecs"
class_url = "/html/body/div[2]/div[2]/div/form/div[3]/div/div[1]/a[2]/img"
info_xpath = "/html/body/div[2]/div[2]/div/form/div[2]/div/div[1]/div[2]/button"
kids_xpath = "/html/body/div[2]/div[2]/div/form/div[2]/div/div[2]/ul/li[1]/div/ul/li[2]/label"
index_xpath = '/html/body/div[2]/div[2]/div/form/fieldset/input[4]'
find_xpath = "/html/body/div[2]/div[2]/div/form/fieldset/a/img"
driver.get(url)
driver.find_element(By.XPATH, info_xpath).click()
driver.find_element(By.XPATH, kids_xpath).click()
driver.find_element(By.XPATH, index_xpath).send_keys(text_keyword)
driver.find_element(By.XPATH, find_xpath).click()
time.sleep(2)
driver.find_element(By.XPATH, class_url).click()
text_keyword_dir = "C:\\Users\\82109\\Desktop\\shop\\men\\" + text_keyword
if not os.path.isdir(text_keyword_dir):  # 없으면 새로 생성하는 조건문
    os.mkdir(text_keyword_dir)

time.sleep(3)
html = driver.page_source
soup = bs(html, 'html.parser')
img_site_list = soup.select_one('tbody').select('tr td.conTd ul li.txt a')
jpg_alt_list = set()
for img in img_site_list:
    url = "https://store-kr.uniqlo.com/"
    link = img['href']
    driver.get(url + link)
    # time.sleep(3)
    soup = bs(driver.page_source, 'html.parser')
    dir_text_name = soup.select_one('#goodsNmArea').text.replace("\t", "").replace("\n", "").replace("/", "")
    dir = "C:\\Users\\82109\\Desktop\\shop\\men\\" + text_keyword + "\\" + dir_text_name
    img_folder = dir
    if not os.path.isdir(img_folder):  # 없으면 새로 생성하는 조건문
        os.mkdir(img_folder)
    jpg_list = soup.select('ul.listChip li a img')
    time.sleep(2)
    item_info = soup.select_one('dl.spec').text
    item_name = dir_text_name
    item_price = soup.select_one("ul.basic li.price p").text.replace("원", "").replace(",", "")
    for jpg in jpg_list:
        # print(jpg['src'])
        jpg_src = jpg['src']
        jpg_link = jpg_src.replace("/chip/chip_", "/").rstrip(".jpg") + "_1000.jpg"
        jpg_alt = jpg['alt'].replace(" ", "").replace("/", "").replace("({0})".format(dir_text_name), "")
        # 여기서도 dir 만들어야함
        # 여기다가 사진 넣어야함
        path = img_folder + "\\" + jpg_alt + ".jpg"
        request.urlretrieve(jpg_link, path)
        jpg_alt_list.add(jpg_alt)
    #
    # url = "http://localhost:8181/itemList"
    # driver.get(url)
    # class_url = "/html/body/section/aside[2]/main/div/form/input[1]"
    # driver.find_element(By.XPATH, class_url).send_keys("93")
    # class_url = "/html/body/section/aside[2]/main/div/form/input[2]"
    # driver.find_element(By.XPATH, class_url).send_keys(item_name)
    # class_url = "/html/body/section/aside[2]/main/div/form/input[3]"
    # driver.find_element(By.XPATH, class_url).send_keys(item_price)
    # class_url = "/html/body/section/aside[2]/main/div/form/input[4]"
    # driver.find_element(By.XPATH, class_url).send_keys(item_info)
    # class_url = "/html/body/section/aside[2]/main/div/form/input[5]"
    # driver.find_element(By.XPATH, class_url).send_keys(img_folder)
    # time.sleep(2)
    # driver.find_element(By.XPATH, "/html/body/section/aside[2]/main/div/form/a").click()

