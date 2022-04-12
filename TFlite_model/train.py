import tensorflow
import tensorboard

from keras.datasets import mnist

(train_x, train_y) , (test_x, test_y)= mnist.load_data()

print("Train_X shape: {}\nTrain_Y shape: {}\nTest_X shape: {}\nTest_Y shape: {}".format(train_x.shape, train_y.shape,test_x.shape,test_y.shape))

