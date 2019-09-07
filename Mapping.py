import codecs
import time


import sys
from networktables import NetworkTables

import logging
import tkinter as tk
from tkinter import messagebox
from tkinter import filedialog

root = tk.Tk()
messagebox.showinfo('Warning','请选择小地图创建的txt文件')

root.withdraw()
file_path = filedialog.askopenfilename()



logging.basicConfig(level=logging.DEBUG)

NetworkTables.initialize(server='roborio-5449-frc.local')
sd = NetworkTables.getTable('SmartDashboard')

X = []
Y = []
Z = []


while True:
        
    a = sd.getNumber('X','N/A')
    b = sd.getNumber('Y','N/A')
    c = sd.getNumber('Heading','N/A')
    
    X.append(a)
    Y.append(b)
    Z.append(c)
    
        
    with codecs.open(file_path,'w','gbk') as f:
       
        f.writelines('{0} {1} {2}'.format(X[-1],Y[-1],Z[-1]))
        
 

