from PIL import Image, ImageOps
import numpy as np
import sys

image1 = Image.open(sys.argv[1])
image2 = Image.open(sys.argv[2])

size = (224, 224)
image1 = ImageOps.fit(image1, size, Image.ANTIALIAS)
image2 = ImageOps.fit(image2, size, Image.ANTIALIAS)

image_array1 = np.asarray(image1)
image_array2 = np.asarray(image2)

image1.show()
image2.show()

normalized_image_array1 = (image_array1.astype(np.float32) / 127.0) - 1
normalized_image_array2 = (image_array2.astype(np.float32) / 127.0) - 1
print(np.sum((normalized_image_array1-normalized_image_array2)**2))
