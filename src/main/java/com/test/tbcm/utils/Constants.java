package com.test.tbcm.utils;

public class Constants {

    public static class Language {
        public static long VIETNAMESE = 1;
        public static long ENGLISH = 2;
    }

    public static class Page {
        public static int DEFAULT_PAGE = 0;

        public static int DEFAULT_OFFSET = 10;
        public static int DEFAULT_MIN_OFFSET = 10;
        public static int DEFAULT_MAX_OFFSET = 99999999;
        public static int DEFAULT_TOTAL_PAGES = 0;
        public static long DEFAULT_TOTAL_ELEMENTS = 0;

        public static CPage validate(int page, int offset) {
            if (page < 0) {
                page = Math.max(page, Page.DEFAULT_PAGE);
            }
            if (offset < 0) {
                offset = Math.max(offset, Page.DEFAULT_OFFSET);
            }
            offset = Math.min(offset, Page.DEFAULT_MAX_OFFSET);
            offset = Math.max(offset, Page.DEFAULT_MIN_OFFSET);
            return new CPage(page, offset);
        }

    }

    public static class Role {
        public static final String ROLE_ADMIN = "ADMIN";
        public static final String ROLE_MANAGER = "MANAGER";
        public static final String ROLE_USER = "USER";

    }


    public static class Messages {

        public static final long MESSAGE_NOT_FOUND = 1L;

        public static final long ROLE_NOT_FOUND = 2L;

        public static final long USER_NOT_FOUND = 3L;

        public static final long ERROR_NOT_FOUND = 4L;

        public static final long AUTHENTICATION_EXCEPTION = 5l;


    }
}
