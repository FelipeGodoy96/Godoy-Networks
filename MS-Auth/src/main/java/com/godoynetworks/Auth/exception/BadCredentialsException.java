package com.godoynetworks.Auth.exception;

    public class BadCredentialsException extends RuntimeException {
        private final String username;
        private final int status;

        public BadCredentialsException(String username, int status) {
            super(String.format("Invalid credentials for user: '%s'", username));
            this.username = username;
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public int getStatus() {
            return status;
        }
    }

