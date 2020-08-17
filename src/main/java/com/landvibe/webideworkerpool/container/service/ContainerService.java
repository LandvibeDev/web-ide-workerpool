package com.landvibe.webideworkerpool.container.service;

import com.landvibe.webideworkerpool.container.model.Container;
import com.landvibe.webideworkerpool.container.repository.ContainerRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    private ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> getContainers(String userId, int limit, int skip) {
        // TODO: apply limit, skip
        return containerRepository.findAllByUserId(userId);
    }

    public Optional<Container> getContainer(String containerId) {
        return containerRepository.findById(containerId);
    }

    public Container createContainer(Container container) {
        return containerRepository.save(container);
    }

    public Optional<Container> updateContainerStatus(String containerId, String status) {
        return containerRepository.findById(containerId)
                .map((oldContainer) -> {
                    oldContainer.setStatus(status);
                    String time = Instant.now().toString();
                    oldContainer.setUpdatedAt(time);
                    return oldContainer;
                })
                .map((newContainer) -> containerRepository.save(newContainer));
    }

    public void deleteContainer(String containerId) {
        containerRepository.deleteById(containerId);
    }
}
