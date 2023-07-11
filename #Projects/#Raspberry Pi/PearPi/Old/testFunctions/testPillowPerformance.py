import sys
import easygui

from PIL import Image
from PIL import ImageChops
from PIL import ImageOps
from PIL import ImageDraw


img1 = Image.open("pic0.jpg")
img2 = Image.open("ref.jpg")

# draw = ImageDraw.Draw(img2)
# diff_list_tuples = [diff[0:2], diff[2:]] if diff else [(None, None), (None, None)]
# draw.rectangle(diff_list_tuples)
# img2.show()

#Grayscale
# img1 = img1.convert('L')
# img2 = img2.convert('L')

# #Threshold
# img1 = img1.point(lambda p: 255 if p > 3 else 0)
# img2 = img2.point(lambda p: 255 if p > 3 else 0)

#Monochrome
# img1 = img1.convert('1')
# img2 = img2.convert('1')

diff = ImageChops.difference(img1, img2)

if diff.getbbox():
    diff.show()

# **************************************************************
# diff = ImageChops.difference(img1, img2).getbbox()
# draw = ImageDraw.Draw(img2)
# diff_list = list(diff) if diff else []
# draw.rectangle(diff_list)
# img2.convert('RGB').save('img2.jpg')
# **************************************************************

# **************************************************************
# diff = ImageChops.difference(im1, im2).getbbox()
# draw = ImageDraw.Draw(im2)
# draw.rectangle(diff)
# im2.convert('RGB').save('file3.jpg')
# **************************************************************

# **************************************************************
# def are_images_equal(img1, img2):
#     equal_size = img1.height == img2.height and img1.width == img2.width

#     if img1.mode == img2.mode == "RGBA":
#         img1_alphas = [pixel[3] for pixel in img1.getdata()]
#         img2_alphas = [pixel[3] for pixel in img2.getdata()]
#         equal_alphas = img1_alphas == img2_alphas
#     else:
#         equal_alphas = True

#     equal_content = not ImageChops.difference(
#         img1.convert("RGB"), img2.convert("RGB")
#     ).getbbox()

#     return equal_size and equal_alphas and equal_content
# **************************************************************

# **************************************************************
# from PIL import Image

# im1 = Image.open('image1.jpg')
# im2 = Image.open('image2.jpg')

# if list(im1.getdata()) == list(im2.getdata()):
#     print("Identical")
# else:
#     print ("Different")
# **************************************************************

