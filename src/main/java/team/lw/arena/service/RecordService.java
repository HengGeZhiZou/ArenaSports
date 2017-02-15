package team.lw.arena.service;

import team.lw.arena.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> findByPage(Integer currPage);

    int getCount();
}
