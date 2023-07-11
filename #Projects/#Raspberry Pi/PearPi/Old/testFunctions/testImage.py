from tkinter import *
from PIL import ImageTk, Image
root = Tk()

canv = Canvas(root, width=150, height=150, bg='white')
canv.grid(row=2, column=3)

img = ImageTk.PhotoImage(Image.open("video.jpg"))  # PIL solution
canv.create_image(0, 0, anchor=NW, image=img)

mainloop()