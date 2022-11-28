import React, { useState} from 'react';
import { Link, useNavigate } from "react-router-dom";
import { styled } from '@mui/system';
import NavBar from "../components/NavBar.js"
import Logo from '../assets/logo.png'
import Icon from '../assets/Saly-1.png'
import { Button, TextField } from '@mui/material';

const Page = styled('div')({
  height:"100vh",
})

const Header = styled('div')({
  display:"flex",
  width:"100%",
  justifyContent:"center",
  alignItems:"center",
  gap:"100px",
  position:"relative",
  top:"10%"
})

const TextContainer = styled('div')({
  display:"flex",
  flexDirection:"column",
  gap:"20px"
})

const Title = styled('div')({
  fontFamily: "Bebas Neue, cursive",
  fontSize:"72px"
})

const SubTitle = styled('div')({
  fontFamily: "Roboto, sans-serif",
  color:"#0F1020",
  fontWeight:"100",
  fontSize:"24px"
})

const Search = styled(Button)({
    borderRadius:"0px",
    fontFamily: "Roboto, sans-serif",
})


const Home = () => {
  const navigate = useNavigate();

  return(
    <Page>
      <NavBar></NavBar>
      <Header>
        <img src={Icon} width="450px"></img>
        <TextContainer>
          <Title>
            SEARCH FOR A MOVIE
          </Title>
          <TextField></TextField>
          <Search variant="contained">SEARCH</Search>
        </TextContainer>
      </Header>
    </Page>
  );
}
export default Home;
