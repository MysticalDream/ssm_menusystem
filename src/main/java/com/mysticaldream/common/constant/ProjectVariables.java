package com.mysticaldream.common.constant;

/**
 * @description: ProjectVariables
 * @date: 2022/5/29 9:50
 * @author: MysticalDream
 */
public class ProjectVariables {
    /**
     * 当前工作路径
     */
    public static String WORK_PATH = System.getProperty("user.dir");

    /**
     * 根路径
     */
    public static String ROOT = "D:/menu_system_resources";
    /**
     * 菜谱图片路径
     */
    public static String MENU_IMG_PATH = ROOT + "/menu_img";

    /**
     * 头像路径
     */

    public static String AVATAR_PATH = ROOT + "/avatar";

    public static String MENU_RESOURCE = "/resources/menu_img";

    public static String AVATAR_RESOURCE = "/resources/avatar";

    public static void setROOT(String ROOT) {
        ProjectVariables.ROOT = ROOT;
        ProjectVariables.MENU_IMG_PATH = ROOT + "/menu_img";
        ProjectVariables.AVATAR_PATH = ROOT + "/avatar";
    }
}
