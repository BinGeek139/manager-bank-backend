package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.Role;
import com.ptit.managerbank.model.CustomUserDetails;
import com.ptit.managerbank.model.Staff;
import com.ptit.managerbank.repository.StaffRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff=staffRepository.findFirstByUserName(username);
        if(staff == null){
            throw new UsernameNotFoundException("User not found");
        }
        return  new CustomUserDetails(staff);
    }
    @Transactional
    public UserDetails loadUserById(Integer id) {

        Staff staff=staffRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(staff);
    }
}
