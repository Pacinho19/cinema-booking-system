package pl.pacinho.cinemabookingsystem.ticket.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.ticket.payment.model.entity.TicketPayment;

@Repository
interface TicketPaymentJpaRepository extends TicketPaymentRepository, JpaRepository<TicketPayment, Integer> {
}
