from importlib.resources import path
import pathlib
import matplotlib.pyplot as plt
import numpy as np
import os
import PIL
import tensorflow as tf

from tensorflow import keras
from tensorflow.keras import layers
from tensorflow.keras.models import Sequential

data_dir = pathlib.Path("dataset/")
print(data_dir)

batch_size = 16
img_h = 1599
img_w = 738

train_ds = tf.keras.utils.image_dataset_from_directory(
    data_dir,
    validation_split = 0.2,
    subset = 'training',
    seed = 123,
    image_size = (img_h,img_w),
    batch_size = 16
)


val_ds  = tf.keras.utils.image_dataset_from_directory(
    data_dir,
    validation_split = 0.2,
    subset = 'validation',
    seed = 123,
    image_size = (img_h,img_w),
    batch_size = 16
)

class_names = train_ds.class_names
print("Class names ",class_names)

plt.figure(figsize=(10,10))
for images, labels in train_ds.take(1):
    for i in range(9):
        ax = plt.subplot(3,3, i + 1)
        plt.imshow(images[i].numpy().astype("uint8"))
        plt.title(class_names[labels[i]])
        plt.axis("off")

for images_batch, labels_batch in train_ds:
    print(images_batch.shape)
    print(labels_batch.shape)
    break


AUTOTUNE = tf.data.AUTOTUNE

train_ds = train_ds.cache().shuffle(1000).prefetch(buffer_size=AUTOTUNE)
val_ds = val_ds.cache().prefetch(buffer_size=AUTOTUNE)

norm_layer = layers.Rescaling(1./255)
norm_ds = train_ds.map(lambda x,y: (norm_layer(x),y))
images_batch,labels_batch = next(iter(norm_ds))
first_image = images_batch[0]

print(np.min(first_image), np.max(first_image))


num_classes = len(class_names)

data_augmentation = keras.Sequential(
  [
    layers.RandomFlip("horizontal",
                      input_shape=(img_h,
                                  img_w,
                                  3)),
    layers.RandomRotation(0.1),
    layers.RandomZoom(0.1),
  ]
)

model = Sequential([
  data_augmentation,
  layers.Rescaling(1./255),
  layers.Conv2D(16, 3, padding='same', activation='relu'),
  layers.MaxPooling2D(),
  layers.Conv2D(32, 3, padding='same', activation='relu'),
  layers.MaxPooling2D(),
  layers.Conv2D(64, 3, padding='same', activation='relu'),
  layers.MaxPooling2D(),
  layers.Dropout(0.2),
  layers.Flatten(),
  layers.Dense(128, activation='relu'),
  layers.Dense(num_classes)
])

model.compile(optimizer='adam',
              loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])

model.summary()

epochs = 11
history = model.fit(
  train_ds,
  validation_data=val_ds,
  epochs=epochs
)


img_test = tf.keras.utils.load_img(
    'dataset/raspberry/WhatsApp Image 2022-04-19 at 3.31.04 PM (1).jpeg', target_size=(img_h,img_w)
)

img_array = tf.keras.utils.img_to_array(img_test)
img_array = tf.expand_dims(img_array,0)

preds = model.predict(img_array)
score = tf.nn.softmax(preds[0])


print("Image labeled as {}".format(class_names[np.argmax(score)]))

saved_model_dir = 'saved_model/board_model'
model.save(saved_model_dir)

converter = tf.lite.TFLiteConverter.from_saved_model(saved_model_dir)
tflite_model = converter.convert()

with open('board_model.tflite','wb') as f:
    f.write(tflite_model)