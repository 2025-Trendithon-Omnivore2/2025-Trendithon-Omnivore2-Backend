package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.CakeCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.UpdateCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.SaveCandleResponse;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CakeCandleService {

    private final CakeCandleRepository cakeCandleRepository;
    private final CakeRepository cakeRepository;
    private final S3Service s3Service;

    @Transactional
    public SaveCandleResponse saveCandle(String email, CakeCandleRequest dto, MultipartFile img) throws IOException {
        String imgUrl = s3Service.uploadImg(img, email);

        Cake cake = cakeRepository.findByMemberEmail(email)
                .orElseThrow(CakeNotFoundException::new);

        CakeCandle candle = CakeCandle.createCandle(cake, dto.content(), imgUrl);

        cakeCandleRepository.save(candle);

        return SaveCandleResponse.builder()
                .candleId(candle.getId())
                .build();
    }

    public CakeCandleResponse findById(Long candleId) {
        return cakeCandleRepository.findById(candleId)
                .map(candle -> CakeCandleResponse.builder()
                        .candleId(candle.getId())
                        .imgUrl(candle.getImgUrl())
                        .content(candle.getContent()).build())
                .orElseThrow(CandleNotFoundException::new);
    }

    public List<CakeCandleResponse> findAllCandle(Long cakeId) {
        return cakeCandleRepository.findByCakeId(cakeId).stream()
                .map(candle -> CakeCandleResponse.builder()
                        .candleId(candle.getId())
                        .imgUrl(candle.getImgUrl())
                        .content(candle.getContent())
                        .build()
                ).toList();
    }

    @Transactional
    public void candleUpdate(String email, UpdateCandleRequest dto, MultipartFile img) throws IOException {
        String imgUrl = s3Service.uploadImg(img, email);

        CakeCandle cakeCandle = cakeCandleRepository.findById(dto.candleId())
                .orElseThrow(CandleNotFoundException::new);

        cakeCandle.update(dto.content(), imgUrl);

        cakeCandleRepository.save(cakeCandle);
    }
}
