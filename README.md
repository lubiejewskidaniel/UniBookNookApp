I had an issue with the database implementation, and unfortunately, I wasn't able to resolve it.
I know that it should be implemented using a relative path, but I encountered problems with saving files to the database. Therefore, I decided to use an absolute path instead.
So, when running the application, please use your own absolute path to the database file, which is located at:
"BookNook/src/main/resources/databases/booknook.db"
Otherwise, the data from the database won't be loaded, and there will be no interaction with it.
