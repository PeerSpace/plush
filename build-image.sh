#!/bin/bash

sbt clean compile stage
docker build -t jeaninejohnson/plush .
