
package tn.esprit.tpfoyer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {
    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<Bloc> listBloc = new ArrayList<>() {
        {
            add(new Bloc("bloc2", 1L,10));
            add(new Bloc("bloc3", 2L,15));
            add(new Bloc("bloc3", 3L,20));

        }
    };


    @Test
    @Order(1)
    void testRetrieveAllBloc() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBloc);
        List<Bloc> listB = blocService.retrieveAllBlocs();
        Assertions.assertEquals(3, listB.size());
    }

    @Test
    @Order(2)
    void testAddBloc() {
        Bloc b = new Bloc( "ff", 55L);
        b.setNomBloc("blocTest");

        Mockito.when(blocRepository.save(Mockito.any(Bloc.class)))
                .thenAnswer(invocation -> {
                    Bloc savedBloc = invocation.getArgument(0);
                    listBloc.add(savedBloc);
                    return savedBloc;
                });

        blocService.addBloc(b);

        Assertions.assertEquals(4, listBloc.size());
    }


    @Test
    @Order(3)
    void testretrieveBlocsSelonCapacite(){
        Bloc bloc1 =new Bloc("bloc2", 1L,10);
        bloc1.setCapaciteBloc(10);

        Bloc bloc2 = new Bloc();
        bloc2.setCapaciteBloc(20);

        Bloc bloc3 = new Bloc();
        bloc3.setCapaciteBloc(5);
        List<Bloc> listBloc = Arrays.asList(bloc1, bloc2, bloc3);
        Mockito.when(blocRepository.findAll()).thenReturn(listBloc);

        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(7);
        Assertions.assertEquals(2, result.size());
    }
}
