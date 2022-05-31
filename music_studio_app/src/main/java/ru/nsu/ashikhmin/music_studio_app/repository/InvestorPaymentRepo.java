package ru.nsu.ashikhmin.music_studio_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ashikhmin.music_studio_app.entity.AdvertisementCompany;
import ru.nsu.ashikhmin.music_studio_app.entity.InvestorPayment;

public interface InvestorPaymentRepo extends JpaRepository<InvestorPayment, Long> {

    Page<InvestorPayment> findAll(Pageable pageable);
}
