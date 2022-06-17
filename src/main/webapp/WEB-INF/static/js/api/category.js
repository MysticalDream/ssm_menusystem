import {axios} from "../common.js";


/**
 * 获取所有分类
 * @returns {Promise<*>}
 */
export async function getAllCategories() {
    let res = await axios.get('/category/all');
    return res.data.data;
}

