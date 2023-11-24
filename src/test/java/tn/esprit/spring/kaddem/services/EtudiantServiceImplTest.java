package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Create a sample list of Etudiant objects
        List<Etudiant> etudiantList = Collections.singletonList(new Etudiant());

        // Mock the repository to return the sample list when findAll is called
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(1, result.size());
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant("Alice", "Johnson");

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertNotNull(addedEtudiant);
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1); // Set the ID for testing

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        assertNotNull(retrievedEtudiant);
        assertEquals(1, retrievedEtudiant.getIdEtudiant().intValue());
    }

    @Test
    public void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1); // Set the ID for testing

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(1);

        // Verify that the delete method was called
        Mockito.verify(etudiantRepository, Mockito.times(1)).delete(etudiant);
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1); // Set the ID for testing

        // Mock the repository to return the etudiant when save is called
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updatedEtudiant);
        assertEquals(1, updatedEtudiant.getIdEtudiant().intValue());
    }

}
