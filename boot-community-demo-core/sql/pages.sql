INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1, 'body', NULL, 'test content', 1);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(2, 'title', NULL, 'About Us', 1);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(3, 'body', NULL, '<h2 style="text-align:center;">This is an example of a content-managed page.</h2><h4 style="text-align:center;"><a href="http://www.broadleafcommerce.com/features/content">Click Here</a> to see more about Content Management in Broadleaf.</h4>', 2);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(4, 'body', NULL, '<h2 style="text-align:center;">This is an example of a content-managed page.</h2>', 3);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1004, 'body', NULL, '<p>Bridge gap solution!</p>', 1002);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1005, 'title', NULL, NULL, 1002);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1006, 'title', NULL, NULL, 1003);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1007, 'body', NULL, '<p>Career councelling</p>', 1003);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1008, 'title', NULL, NULL, 1004);
INSERT INTO PUBLIC.BLC_PAGE_FLD
(PAGE_FLD_ID, FLD_KEY, LOB_VALUE, VALUE, PAGE_ID)
VALUES(1009, 'body', NULL, '<p>About us!</p>', 1004);


INSERT INTO PUBLIC.BLC_PAGE
(PAGE_ID, ACTIVE_END_DATE, ACTIVE_START_DATE, DESCRIPTION, EXCLUDE_FROM_SITE_MAP, FULL_URL, META_DESCRIPTION, META_TITLE, OFFLINE_FLAG, PRIORITY, PAGE_TMPLT_ID)
VALUES(1002, NULL, NULL, 'Bridge gap solution', false, '/bridge-gap-solution', NULL, NULL, false, NULL, 1);
INSERT INTO PUBLIC.BLC_PAGE
(PAGE_ID, ACTIVE_END_DATE, ACTIVE_START_DATE, DESCRIPTION, EXCLUDE_FROM_SITE_MAP, FULL_URL, META_DESCRIPTION, META_TITLE, OFFLINE_FLAG, PRIORITY, PAGE_TMPLT_ID)
VALUES(1003, NULL, NULL, 'Career councelling', false, '/career-councelling', NULL, NULL, false, NULL, 1);
INSERT INTO PUBLIC.BLC_PAGE
(PAGE_ID, ACTIVE_END_DATE, ACTIVE_START_DATE, DESCRIPTION, EXCLUDE_FROM_SITE_MAP, FULL_URL, META_DESCRIPTION, META_TITLE, OFFLINE_FLAG, PRIORITY, PAGE_TMPLT_ID)
VALUES(1004, NULL, NULL, 'About us', false, '/about-us', NULL, NULL, false, NULL, 1);