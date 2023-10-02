import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

function Home() {
    const [users,setusers] = useState([]);

    const { id } = useParams();
    useEffect(() => {
        loadUsers();
    },[]);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/users");
        setusers(result.data);
    };

    const deleteUser = async (id) => {
      await axios.delete(`http://localhost:8080/user/${id}`);
      loadUsers();
    };

  return (
    <div className="container">
      <div className="py-4">
        <table class="table border shadow">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">UserName</th>
              <th scope="col">Email</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {
                users.map((item,index) => (
                    <tr>
                        <th scope='row' key={index}>{index+1}</th>
                        <td>{item.name}</td>
                        <td>{item.username}</td>
                        <td>{item.email}</td>
                        <td>
                            <Link className="btn btn-primary mx-2" to={`viewuser/${item.id}`}>View</Link>
                            <Link className="btn btn-outline-primary mx-2" to={`edituser/${item.id}`}>Edit</Link>
                            <button className="btn btn-danger mx-2" onClick={() => deleteUser(item.id)}>Delete</button>
                        </td>
                    </tr>
                ))
            }
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Home;
