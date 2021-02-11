package com.test.tbcm.config;

public class Route {
    public static class V1 {

        private static final String API = "api";
        private static final String VERSION = "v1";
        public static final String SCHEME_DB = "logintest";
        public static final String prefixApi = "/" + API + "/" + VERSION;


        // User API S3
        public static final String SEARCH_USER = "/user/search";
        public static final String LOGIN_USER = "/user/login";
        public static final String GET_USER = "/user";
        public static final String CREATE_USER = "/user";
        public static final String FIND_USER_BY_ID = "/user/{id}";
        public static final String UPDATE_USER = "/user/{id}";
        public static final String DELETE_USER_BY_ID = "/user/{id}";
        public static final String GET_USER_TOP_SELLER_PRODUCT = "/user/top-seller";
        public static final String FIND_USER_BY_TOKEN = "/user/token";
        public static final String LOGIN_FACEBOOK = "/auth/login/facebook";
        public static final String LOGIN_FACEBOOK_TEST = "/auth/login-facebook";
        public static final String GET_USER_TOP_BUYER = "/user/top-buyer";

        // Role API
        public static final String GET_ROLE = "/role";
        public static final String CREATE_ROLE = "/role";
        public static final String FIND_ROLE_BY_ID = "/role/{id}";
        public static final String UPDATE_ROLE = "/role/{id}";
        public static final String DELETE_ROLE_BY_ID = "/role/{id}";
        

    }


}
