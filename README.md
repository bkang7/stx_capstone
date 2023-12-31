# Capstone project: Deep-learning-based classification of kidney histology image patches from Spatial transcriptomics data

Project statement: This project is to develop a deep learning based model that can automatically classify image patches corresponding to Visium spatial transcriptomics analysis of kidney tissue. Primary classification is glomeruli vs non-glomeruli patches. Previously widely used bulk RNAseq method used whole tissue area to profile mRNA expression changes. However, this bulk approach showed limited success in detecting changes in substructure of tissue such as glomeruli in the kidney. To overcome this limitation, Spatially resolved transcriptomics technologies such as Visium were developed in recent years. It showed some success in identifying and clustering image patches with changes. However, not many analysis was done with image data since image data is typically requiring high computational resources and time consuming. To tackle this hurdle, I developed a deep learning based image classification model for kidney Visium spatial transcriptomics data which was generated internally in our institution. This will be a proof of principal and foundation of analysis pipeline for future spatial transcriptomics analysis

A Jupyter notebook with the code used for the project was uploaded in this Github. This contains cells with different modules including 1) data collection and image patch generation for training (training, validation, and test sets), 2) image patch extraction from Spatial transcriptomics, 3) creating datasets and data-loaders for deep learning models, 4) loading the ResNet50 pretrained with ImageNet version2, 5) fine tuning parameters with training, 6) visualize training result, 7) test with test dataset, and 8) applying model to Sptial transcriptomics image patches and manual validation.

# Execution of code
In order to execute the notebook, the following libraries are required: tifffile, pandas, numpy, geojson, json, math, matplotlib, tqdm, shutil,glob, os, random, typing, sklearn.metrics, mpl_toolkits, torch and albumentations. 
Spatial Transcriptomics data and whole slide images are stored in the 'data_root'; once data is publicalluy available, 'data_root' should be changed to the directory where the data is stored.

Jupyter notebook: STX_capstone_main.ipynb
Groovy script: mask_export_groovy_v3_individual_class.groovy

Fro the groovy script, in QuPath, Ctrl+[ will open script editor and the groovy script file can be loaded.  

# Data access statement 
Data used in this project are from priovately owned data by my current employer. We are planning to publish this data next year. Once it is published, the data will be available to the public. 
