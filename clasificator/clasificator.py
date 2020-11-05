import tensorflow.keras
from PIL import Image, ImageOps
import numpy as np
import sys
import os
from os import listdir
from os.path import isfile, join
import time#only for tests

if len(sys.argv)  != 2:#check pass argunents length
    print("usage: clasificator.py directory")
    sys.exit()

np.set_printoptions(suppress=True)# Disable scientific notation for clarity
model = tensorflow.keras.models.load_model('keras_model.h5', compile=False)# Load the model
directory = sys.argv[1]#set the working directory
clases = ['ife', 'pass']#set the classes
acceptancePorcentage = 0.70 #set the acceptance porcentage
start_time = time.time()
for file in listdir(directory):
    # Create the array of the right shape to feed into the keras model
    # The 'length' or number of images you can put into the array is determined by the first position in the shape tuple, in this case 1.
    data = np.ndarray(shape=(1, 224, 224, 3), dtype=np.float32)
    image = Image.open(directory+file)# Path to the imageer
    size = (224, 224)#resize the image to a 224x224
    image = ImageOps.fit(image, size, Image.ANTIALIAS)
    image_array = np.asarray(image)#turn the image into a numpy array
    #image.show()#display the resized image
    normalized_image_array = (image_array.astype(np.float32) / 127.0) - 1# Normalize the image
    data[0] = normalized_image_array # Load the image into the array
    # run the inference
    prediction = model.predict(data)
    clase = ''
    for i in range(len(clases)):
        if prediction[0][i] > acceptancePorcentage:
            clase = clases[i]
    os.rename(directory+file,directory+clase+file)
    #print(prediction)
print("--- %s seconds ---" % (time.time() - start_time))
