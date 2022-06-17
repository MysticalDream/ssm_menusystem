import {axios} from "../common.js";

/**
 * 获取菜谱详情
 * @param id
 */
export async function getMenuDetail(id) {
    let detail = await axios.get('/menus/detail', {params: {id: id}});
    return detail.data.data;
}


export async function deleteMenu(id) {

    const res = await axios.post('/menus/remove/' + id);
    return res.data.code === 200;
}

export async function deleteMenuBatch(ids) {

    const res = await axios.post('/menus/removeBatch', ids);
    return res.data.code === 200;
}