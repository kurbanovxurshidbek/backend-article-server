package com.article.article.service;

import com.article.article.exception.ResourceNotFoundException;
import com.article.article.model.entity.Article;
import com.article.article.model.entity.Hashtag;
import com.article.article.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public void saveHashtag(String name) {
        Optional<Hashtag> optionalHashtag = hashtagRepository.findByName(name);
        if (optionalHashtag.isEmpty()) {
            Hashtag hashtag = Hashtag.of(name);
            hashtagRepository.save(hashtag);
        }
    }

    public Set<Hashtag> findHashtagsByNames(Set<String> hashtagNames) {
        return new HashSet<>(hashtagRepository.findByNameIn(hashtagNames));
    }

    public Set<String> parseHashtagNames(String content) {
        if (content == null) {
            return Set.of();
        }

        Pattern pattern = Pattern.compile("#[\\wa-z]+");
        Matcher matcher = pattern.matcher(content.strip());
        Set<String> result = new HashSet<>();

        while (matcher.find()) {
            result.add(matcher.group().replace("#", ""));
        }

        return Set.copyOf(result);
    }

    public void deleteHashtagWithoutArticles(Long id) {
        Hashtag hashtag = hashtagRepository.getReferenceById(id);
        if (hashtag.getArticles().isEmpty()) {
            hashtagRepository.delete(hashtag);
        }
    }

}
