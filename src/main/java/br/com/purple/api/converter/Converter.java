package br.com.purple.api.converter;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<T,R>{
    R from(T t);

    default List<R> from(List<T> list){
        if (list==null) return null;
        return list.stream().map(this::from).collect(Collectors.toList());
    }

    default Page<R> from(Page<T> page){
        if (page == null) return null;
        return page.map(this::from);
    }
}