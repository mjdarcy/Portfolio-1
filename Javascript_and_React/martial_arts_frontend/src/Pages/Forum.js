import React from 'react';
import NavBar from '../Components/NavBar';
import TechList from '../Components/TechList';
import TechVideo from '../Components/TechVideo';
import TechDescription from '../Components/TechDescription';

export default class Forum extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        selectedTechnique:
        {
            id: 0,
            name: "",
            type: "",
            description: "",
            video: "",
            rating: 1
        },
        style:
        {
            borderStyle: "solid"
        },
        data:{ }
    }
    this.setSelectedTechnique = this.setSelectedTechnique.bind(this);
    this.getLatestTechnqiues = this.getLatestTechnqiues.bind(this);
    this.getTopTechniques = this.getTopTechniques.bind(this);
}

    setSelectedTechnique(tech)
    {
        this.setState({ selectedTechnique: tech })
    }

    getTopTechniques()
    {
        fetch(this.props.apiUrl + "/technique/top")
            .then(response => response.json())
            .then(data => this.setState({data: data}));
    }

    getLatestTechnqiues()
    {
        fetch(this.props.apiUrl + "/technique/latest")
        .then(response => response.json())
        .then(data => this.setState({data: data}));
    }

    getAllForeignUserFavorites(first, last)
    {
        fetch(this.props.apiUrl + "/favorite/" + first + "/" + last)
            .then(response => response.json())
            .then(data => this.setState({data: data}));
    }

    postUserFavorite(user, id)
    {
        fetch(this.props.apiUrl + "/favorite",
            {method: "POST",
             headers: {"Content-Type": "application/json"},
             body: JSON.stringify({"username": user, "techId": id})
            }).catch((e) => console.log(e));
    }

    deleteUserFavorite(username, techId)
    {
        fetch(this.props.apiUrl + "/favorite", {method: "DELETE", body: {username: username, techId: techId}})
            .catch((e) => console.log(e));
    }

    render()
    {
        const page = "Forum";
        return(
        <div style={this.state.style}>
            <NavBar page={page}/>
            <TechList apiUrl={this.props.apiUrl} setSelectedTechnique={this.setSelectedTechnique} getTechniques={this.getTopTechniques} page={page}/>
            <TechVideo videoUrl={this.props.apiUrl + "/technique/video/" + this.state.selectedTechnique.id}/>
            <TechDescription desc={this.state.selectedTechnique.description}/>
            <TechList apiUrl={this.props.apiUrl} setSelectedTechnique={this.setSelectedTechnique} getTechniques={this.getLatestTechnqiues} page={page}/>
        </div>
        )
    }
}