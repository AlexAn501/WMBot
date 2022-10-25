package ru.worldmac.wmbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.worldmac.wmbot.entity.GroupSub;

/**
 * {@link Repository} for handling with {@link GroupSub} entity.
 */
@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
