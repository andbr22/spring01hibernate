package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;

public class PublisherConverter implements Converter<String,Publisher> {
    @Autowired
    PublisherDao publisherDao;

    @Override
    public Publisher convert(String s) {
        return publisherDao.readById(Long.valueOf(s));
    }
}
