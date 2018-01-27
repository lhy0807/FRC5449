#!/usr/bin/python3

"""
Detects stronghold goals using a usb camera plugged into raspberry pi
"""
import cv2
import networktables
from networktables import NetworkTables
from rgb_test_demo import GripPipeline
from array import *
import numpy as np


def reflection_tape_detection(pipeline):

    center_x_positions = ['N/A']
    center_y_positions = ['N/A']
    widths = ['N/A']
    heights = ['N/A']

    for contour in pipeline.filter_contours_output:
        x, y, w, h = cv2.boundingRect(contour)
        center_x_positions.append(int(x + w / 2)) 
        center_y_positions.append(int(y + h / 2))
        widths.append(int(w))
        heights.append(int(y))

    table = NetworkTables.getTable("SmartDashBoard")
    table.putValue("centerX", center_x_positions)
    table.putValue("centerY", center_y_positions)
    table.putValue("width", widths)
    table.putValue("height", heights)

    return [center_x_positions,center_y_positions,widths,heights] 


def main():
    print('Initializing NetworkTables')
    NetworkTables.initialize(server='10.54.49.2')
    
    print('Creating video capture')
    cap = cv2.VideoCapture(0)

    print('Creating pipeline')
    pipeline = GripPipeline()

    print('Running pipeline')
    while True:
        have_frame, frame = cap.read()
        if have_frame:
           pipeline.process(frame)
           a = reflection_tape_detection(pipeline)
           print(a)
           for i in range(1,len(a[0])):
               cv2.rectangle(frame,(a[0][i],a[1][i]),(a[0][i]+a[2][i],a[1][i]+a[3][i]),(255,0,0))
                
           cv2.imshow('frame',frame)
           cv2.waitKey(1)           
           

    print('Capture closed')


if __name__ == '__main__':
    main()
