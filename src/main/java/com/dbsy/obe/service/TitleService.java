package com.dbsy.obe.service;

import com.dbsy.obe.pojo.Title;

import java.util.List;
import java.util.Map;

public interface TitleService {
    int insert(Title record);

    int insertSelective(Title record);

    int listCount(Map map);

    List<Title> list(Map map);

    Title get(int id);

    int delete(int id);

    int update(Title title);

    int batchRemove(int[] ids);

    List<Title> getAll();
}
