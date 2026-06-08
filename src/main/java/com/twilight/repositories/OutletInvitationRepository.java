package com.twilight.repositories;

import com.twilight.objects.OutletInvitation;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutletInvitationRepository extends JpaRepository<OutletInvitation,Integer> {
    OutletInvitation findByIdAndInviteeMobileNo(@NonNull Integer invitationId,@NonNull String inviteeMobileNo);

    List<OutletInvitation> findAllByInviteeMobileNo(String inviteeMobileNo);
}
