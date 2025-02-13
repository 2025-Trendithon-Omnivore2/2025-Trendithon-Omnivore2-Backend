package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.CakeCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application.Image.S3Service;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.CakeCandle;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.repository.CakeCandleRepository;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.exception.CandleNotFoundException;
import com.example.test.omnivore2trendithon2025.cake.domain.repository.CakeRepository;
import com.example.test.omnivore2trendithon2025.cake.exception.CakeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class CakeCandleService {

    private final CakeCandleRepository cakeCandleRepository;
    private final CakeRepository cakeRepository;
    private final S3Service s3Service;

    public Long saveCandle(CakeCandleRequest dto, MultipartFile img) throws IOException {
        String imgUrl = s3Service.uploadImg(img, dto.memberId());

        Cake cake = cakeRepository.findById(dto.memberId())
                .orElseThrow(CakeNotFoundException::new);

        CakeCandle candle = CakeCandle.createCandle(cake, dto.content(), imgUrl);

        cakeCandleRepository.save(candle);

        return candle.getId();
    }

    public CakeCandleResponse findById(Long candleId) {
        return cakeCandleRepository.findById(candleId)
                .map(candle -> CakeCandleResponse.builder()
                        .candleId(candle.getId())
                        .imgUrl(candle.getImgUrl())
                        .content(candle.getContent()).build())
                .orElseThrow(CandleNotFoundException::new);
    }
}
