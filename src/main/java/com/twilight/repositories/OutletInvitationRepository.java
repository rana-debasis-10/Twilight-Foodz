package com.twilight.repositories;

import com.twilight.objects.OutletInvitation;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutletInvitationRepository extends JpaRepository<OutletInvitation,Integer> {
    Optional<OutletInvitation> findByIdAndInviteeMobileNo(@NonNull Integer invitationId, @NonNull String inviteeMobileNo);

    Optional<OutletInvitation> findByInviteeMobileNo(String inviteeMobileNo);
}
