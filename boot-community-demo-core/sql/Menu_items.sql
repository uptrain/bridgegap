INSERT INTO PUBLIC.BLC_MEDIA
(MEDIA_ID, ALT_TEXT, TAGS, TITLE, URL)
VALUES(100004, NULL, NULL, NULL, ' ');
INSERT INTO PUBLIC.BLC_MEDIA
(MEDIA_ID, ALT_TEXT, TAGS, TITLE, URL)
VALUES(100005, NULL, NULL, NULL, ' ');
INSERT INTO PUBLIC.BLC_MEDIA
(MEDIA_ID, ALT_TEXT, TAGS, TITLE, URL)
VALUES(100006, NULL, NULL, NULL, ' ');
INSERT INTO PUBLIC.BLC_MEDIA
(MEDIA_ID, ALT_TEXT, TAGS, TITLE, URL)
VALUES(100007, NULL, NULL, NULL, ' ');


INSERT INTO PUBLIC.BLC_CMS_MENU_ITEM
(MENU_ITEM_ID, ACTION_URL, ALT_TEXT, CUSTOM_HTML, LABEL, "SEQUENCE", MENU_ITEM_TYPE, MEDIA_ID, LINKED_MENU_ID, LINKED_PAGE_ID, PARENT_MENU_ID)
VALUES(1004, NULL, NULL, NULL, 'Bridge Gap solution', 2.000000, 'PAGE', 100004, NULL, 1002, 1);
INSERT INTO PUBLIC.BLC_CMS_MENU_ITEM
(MENU_ITEM_ID, ACTION_URL, ALT_TEXT, CUSTOM_HTML, LABEL, "SEQUENCE", MENU_ITEM_TYPE, MEDIA_ID, LINKED_MENU_ID, LINKED_PAGE_ID, PARENT_MENU_ID)
VALUES(1005, NULL, NULL, NULL, 'Career Councelling', 3.000000, 'PAGE', 100005, NULL, 1003, 1);
INSERT INTO PUBLIC.BLC_CMS_MENU_ITEM
(MENU_ITEM_ID, ACTION_URL, ALT_TEXT, CUSTOM_HTML, LABEL, "SEQUENCE", MENU_ITEM_TYPE, MEDIA_ID, LINKED_MENU_ID, LINKED_PAGE_ID, PARENT_MENU_ID)
VALUES(1006, '/contactus', NULL, NULL, 'Contactus', 4.000000, 'LINK', 100006, NULL, NULL, 1);
INSERT INTO PUBLIC.BLC_CMS_MENU_ITEM
(MENU_ITEM_ID, ACTION_URL, ALT_TEXT, CUSTOM_HTML, LABEL, "SEQUENCE", MENU_ITEM_TYPE, MEDIA_ID, LINKED_MENU_ID, LINKED_PAGE_ID, PARENT_MENU_ID)
VALUES(1007, NULL, NULL, NULL, 'About us', 5.000000, 'PAGE', 100007, NULL, 1, 1);
