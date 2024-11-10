
package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {
    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;


    List<Bloc> listBloc = new ArrayList<>() {
        {
            add(new Bloc("bloc2", 10L));
            add(new Bloc("bloc3", 15L));

        }
    };


    @Test
    @Order(1)
    void testRetrieveAllBloc() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBloc);
        List<Bloc> listB = blocService.retrieveAllBlocs();
        Assertions.assertEquals(2, listB.size());
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

        Assertions.assertEquals(3, listBloc.size());
    }
}
