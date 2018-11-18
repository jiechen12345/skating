package com.oppo.business;

import com.oppo.dto.BookDto;
import com.oppo.dto.BookPage;
import com.oppo.dto.PreorderDto;
import com.oppo.dto.PreorderPage;
import com.oppo.request.BookReq;
import com.oppo.request.PreorderReq;

import java.util.List;

/**
 * Created by JieChen on 2018/11/18.
 */
public interface VerfyService {
   // List<PreorderDto> findAll();

    //PreorderDto queryOne(String id);

    PreorderPage getAllForm(Integer page, Integer pageSize);

   // PreorderPage queryAll(Integer page, Integer pageSize, PreorderReq bookReq);
}
