package org.mymusic.core.exception;

public class ServiceException extends RuntimeException {

    ServiceException(String message) {
        super(message);
    }

    public static class LoginNotFoundException extends ServiceException {
        public LoginNotFoundException(String login) {
            super("Login " + login + " not found");
        }
    }

    public static class LoginExistsException extends ServiceException {
        public LoginExistsException(String message) {
            super(message);
        }
    }

    public static class FileUploadException extends ServiceException {
        public FileUploadException(String filename) {
            super("Failed to upload file " + filename);
        }
    }

    public static class FileNotFoundException extends ServiceException {
        public FileNotFoundException(String uuid) {
            super("File " + uuid + " not found");
        }
    }
}
