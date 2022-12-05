import styled from "@emotion/styled";
import React, { useEffect, useState } from "react";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Button from "@mui/material/Button";
import { Navigate, useNavigate } from "react-router";

const MovieListContainer = styled("ul")({
  paddingLeft: "10%",
  paddingRight: "10%",
});

const MovieListItem = styled("li")({
  listStyle: "none",
  borderTop: "solid 1px",
  width: "95%",
  height: "220px",
  display: "flex",
  alignItems: "flex-end",
  marginBottom: "20px",
});

const MovieDetailContainer = styled("div")({
  display: "flex",
  flexDirection: "column",
  font: "Roboto",
  fontSize: 22,
  marginLeft: "25px",
});

const MovieTitle = styled("h3")({
  fontSize: 32,
  height: 20,
  fontFamily: "Roboto, sans-serif",
});

const ViewSeatsButton = styled(Button)({
  backgroundColor: "#E16138",
  color: "white",
  width: "342px",
  height: "48px",
  border: "none",
  fontSize: 16,
  boxShadow: "none",
  borderRadius: "0",
});

const MovieImg = styled("img")({
  width: "130px",
  height: "200px",
});

const ShowtimeText = styled("div")({
  height: 40,
  fontFamily: "Roboto, sans-serif",
});

const DropdownMenuStyle = {
  width: "220px",
  height: "50px",
};

const MovieItem = ({ data, title }) => {
	const navigate = useNavigate();
  const [time, setTime] = useState(0);
	const [showtimeID, setShowtimeID] = useState('');

  const handleTimeChange = (event) => {
		setTime(event.target.value);
  };

	const handleMenuClick = ( id ) => {
		setShowtimeID(id);
	}

	const convertTime = ( time ) => {
		let unixtime = Date.parse(time);
		let selectedTime = new Intl.DateTimeFormat('en-US', {hour: '2-digit', 
		minute: '2-digit', second: '2-digit'}).format(unixtime);
		return selectedTime;
	}

  const handleClick = ( id, name ) => {
		navigate("/seatselection", {state: 
			{
				movieID: id, 
				title: name, 
				showtime: {time: time, showtimeId: showtimeID}
			}});
  };

  return (
    <>
      <MovieListContainer>
        {data
          .filter((data) => data.name.toLowerCase().includes(title))
          .map((data) => (
            <MovieListItem key={data.id}>
              <MovieImg src={data.cover} />
              <MovieDetailContainer>
                <MovieTitle>{data.name}</MovieTitle>
                <ShowtimeText>Showtimes</ShowtimeText>
                <FormControl sx={DropdownMenuStyle} size="small">
                  <Select value={time} onChange={(event) => handleTimeChange(event)}>
                    {data.showtimes.map((showtime) => (
                      <MenuItem key={showtime.showtimeID} value={convertTime(showtime.timestamp)} 
											onClick={() => handleMenuClick(showtime.showtimeID)}>
                        {convertTime(showtime.timestamp)}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <ViewSeatsButton
                  variant="contained"
                  onClick={() => handleClick(data.id, data.name)}
                >
                  VIEW SEATS
                </ViewSeatsButton>
              </MovieDetailContainer>
            </MovieListItem>
          ))}
      </MovieListContainer>
    </>
  );
};

export default MovieItem;
