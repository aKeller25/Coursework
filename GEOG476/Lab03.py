#!/usr/bin/env python
# coding: utf-8

# Class: GEOG476
# Semester: FALL2018
# Assignment: Lab03
# Author: Alex Keller
# Written: 10:55 AM 9/19/2018
# Last edited: 11:01 PM 9/25/2018

# Notes: points are floating types
# 
# rect = make sure the x and the y aren't the same
# 
# have self, and x1, x2, y1, y2 for rect
# 
# distance = take a point or x,y coordinates
# 
# [negative coordinates are allowed & Use Points for parameters for rectangles, triangles, and circles]

# In[42]:


import math

class Point:    
    """This class is used to represent a point in 2D space"""
    
    def __init__(self, x: float = 0.0, y : float = 0.0):
        """This function is used to create a point in 2D space"""
        
        self.x = x 
        self.y = y
    
    def move(self, x: float, y: float):
        """This function is used to move a point in 2D space"""
        
        self.x = x
        self.y = y
        
    def distance_to_point(self, new_point: Point) -> float:
        """This function is used to calculate the distance between the point and a different point in 2D space"""
        
        return math.sqrt((new_point.x - self.x)**2 + (new_point.y - self.y)**2)

#print(help(Point))

p = Point(0, 0)

p_other = Point(5,5) 

p.move(0, 2)

print("The distance from the point at the location ({}, {}) to (5,5) is {}. \n".format(p.x, p.y, p.distance_to_point(p_other)))


# In[61]:


class Rectangle:
    """This class is used to represent a rectangle in 2D space"""

    def __init__(self, x1: float = 0.0, y1: float = 0.0, x2: float = 1.0, y2: float = 1.0):
        """This function is used to create a rectangle in 2D space"""
      
        if((x1 != x2) & (y1 != y2)):
            self.x1 = x1
            self.y1 = y1
            self.x2 = x2
            self.y2 = y2

    def get_area(self) -> float:
        """This function is used to get the area of the created rectangle"""
        
        return abs((self.x2 - self.x1) * abs(self.y2 - self.y1))
        
    def get_perimeter(self) -> float:
        """This function is used to get the perimeter of the created rectangle"""
        
        return ((2 * abs(self.x2 - self.x1)) + (2 * abs(self.y2 - self.y1)))

p1 = Point(0, 0)
p2 = Point(2, 2)


rect = Rectangle(p1.x, p1.y, p2.x, p2.y)

print("Area of the rectangle = ", rect.get_area())
print("Perimeter of the rectangle = ", rect.get_perimeter())


# In[92]:


class Triangle:
    """This class is used to represent a triangle in 2D space"""
    
    def __init__(self, x1: float = 0.0, y1: float = 0.0, x2: float = 1.0, y2: float = 1.0, x3: float = 0.0, y3: float = 1.0):
        """This function is used create a triangle in 2D space"""
        
        self.x1 = x1
        self.y1 = y1
        self.x2 = x2
        self.y2 = y2
        self.x3 = x3
        self.y3 = y3
                 
    def get_area(self) -> float:  
        """This function is used to get the area of the created triangle"""
        
        return abs((self.x1 * (self.y2 - self.y3)) + (self.x2 * (self.y3 - self.y1)) + (self.x3 * (self.y1 - self.y2))) / 2
    
    def get_perimeter(self) -> float:
        """This function is used to get the perimeter of the created triangle"""
        line_one = math.sqrt((self.x2 - self.x1)**2 + (self.y2 - self.x1)**2)
        line_two = math.sqrt((self.x3 - self.x1)**2 + (self.y3 - self.x1)**2)
        line_three = math.sqrt((self.x2 - self.x2)**2 + (self.y3 - self.x2)**2)
        
        return (line_one + line_two + line_three)
    
    
pt1 = Point(0, 0)
pt2 = Point(5, 10)
pt3 = Point(10, 0)

tri = Triangle(pt1.x, pt1.y, pt2.x, pt2.y, pt3.x, pt3.y)

           
print("Area of the triangle = {} \n".format(tri.get_area()))
print("Perimeter of the triangle = {} \n".format(tri.get_perimeter()))


# In[93]:


class Circle:
    """This class is used to represent a circle in 2D space"""
    
    def __init__(self, x_center: float = 0.0, y_center: float = 0.0, radius: float = 1.0):
        """This function is used create a triangle in 2D space"""
        
        if (radius != 0):
            self.x_center = x_center
            self.y_center = y_center
            self.radius = radius
        
    def get_area(self) -> float:
        """This function is used to get the area of the created circle"""
        
        return (3.14 * (self.radius**2))
    
    def get_perimeter(self) -> float:
        """This function is used to get the perimeter/circumference of the created circle"""
        
        return (2 * 3.14 * self.radius)

pc1 = Point(5,5)
    
circ = Circle(pc1.x, pc1.y, 5)

print("Area of the circle = ", circ.get_area())
print("Perimeter of the circle = ", circ.get_perimeter())    


# In[ ]:




